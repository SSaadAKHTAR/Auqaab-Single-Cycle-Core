package SingleCycle
import chisel3._
import chisel3.util._ 
import javax.xml.transform.OutputKeys

class fetch extends Module{
    val io = IO(new Bundle{
        val pcsel = Input(Bool())
        val aluout = Input(SInt(32.W))
        val bform = Input(Bool())
        val jal = Input(Bool())
        val jalr = Input(Bool())
        val ins = Input(UInt(32.W))
        val instruction = Output(UInt(32.W))
        val pc_out = Output (UInt(32.W))
        val pc4_out = Output (UInt(32.W))

    })

    val pc =RegInit(4.U(32.W))
    // val SyncMem = Module(new InstMem("/home/saad/Desktop/Single cycle/Scala-Chisel-Learning-Journey/src/main/scala/gcd/SingleCycle/imem.txt"))
    val op = WireInit(SyncMem.io.inst(6,0))
    // when(op==="b1100011".U){
    //     io.bform:=1.B
    //     io.jal:=0.B
    //     io.jalr:=0.B
    // }
    // .elsewhen(op==="b1101111".U){//Jal 
    //     io.bform:=0.B
    //     io.jal:=1.B
    //     io.jalr:=0.B
    
    // }
    // .elsewhen(op==="b1100111".U){//jalr
    //     io.bform:=0.B
    //     io.jal:=0.B
    //     io.jalr:=1.B
    
    // }
    // .otherwise{
    //     io.bform:=0.B
    //     io.jal:=0.B
    //     io.jalr:=0.B
    // }


        // val install = Reg(UInt(32.W))
        // install:=pc
        // io.pc_out:=install
        // SyncMem.io.addr:=install
        // pc:=install
    
    
        io.pc_out:= pc
        // SyncMem.io.addr:= pc
        

    
    pc:=Mux(io.pcsel,io.aluout.asUInt()+4.U,pc + 4.U)
    
    
    io.pc4_out:= pc + 4.U
    
    io.instruction:= io.ins
}