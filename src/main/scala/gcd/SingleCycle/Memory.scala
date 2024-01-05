package SingleCycle
import chisel3._
import chisel3.util._

class memory extends Module {
    val io = IO(new Bundle{
        // val Wen = Input(Bool())
        // val addr = Input(UInt(32.W))
        // val Din = Input(SInt(32.W))
        
        // val fun3 = Input(UInt(3.W))
        // val enable = Input(Bool())

        val out = Input(SInt(32.W))
        val br_taken = Input(Bool())
        val Wen = Input(Bool())
        val Fun3 = Input(UInt(3.W))
        val Enable = Input(Bool())
        val Din = Input (SInt(32.W))
        val MemToReg = Input(UInt(2.W))
        val RDout = Input(UInt(5.W))
        val RS2out = Input(UInt(5.W)) 
        val RegWr_enout = Input(Bool())
        val pco = Input(UInt(32.W))
        val pcselout = Input(Bool())
        val bra = Input(Bool())
        val jal = Input(Bool())
        val jalr = Input(Bool())

        val RegWr_enOut = Output(Bool())
        val memtoreg = Output(UInt(2.W))
        val RDsel=Output(UInt(5.W))
        val Memrd=Output(Bool())
        val Dout = Output(SInt(32.W))
        val alu_out = Output(SInt(32.W))
        val pcout = Output(UInt(32.W))
        val brtaken = Output(Bool())
        val pcsel = Output(Bool())
        val bform = Output(Bool())
        val jalform = Output(Bool())
        val jalrform = Output(Bool())


    })
    val D_Mem = Module(new Datamem)
    io.pcout:=io.pco
    io.pcsel:=io.pcselout

    io.bform:=io.bra
    io.jalform:=io.jal
    io.jalrform:=io.jalr

    D_Mem.io.Wen:=io.Wen
    D_Mem.io.addr:=io.out.asUInt()
    D_Mem.io.Din:=io.Din
    D_Mem.io.fun3:=io.Fun3
    D_Mem.io.enable:=io.Enable

    io.Dout:=D_Mem.io.Dout

    io.RegWr_enOut:=io.RegWr_enout
    io.memtoreg:=io.MemToReg
    io.RDsel:=io.RDout
    io.Memrd:=io.Enable
    io.alu_out:=io.out

    io.brtaken:=io.br_taken
}