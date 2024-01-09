package SingleCycle
import chisel3._ 
import chisel3.util._

class Top extends Module{
    val io = IO(new Bundle{
        val din = Input(SInt(32.W))
        val out = Output(SInt(32.W))
        // val eout = Output(UInt(32.W))
    })
    val TopCore = Module(new TopModule)
    val syncMem = Module(new InstMem("./src/main/scala/gcd/SingleCycle/imem.txt")) 
    val check = Module(new check)
    dontTouch(TopCore.io) 
    io.out:=0.S

    check.io.insin := syncMem.io.inst
    check.io.pcselc := TopCore.io.pcsel
    
    syncMem.io.addr:= TopCore.io.pcout
    TopCore.io.ins:=    check.io.insout
    // datapath.io.ins:=io.eout

    

}