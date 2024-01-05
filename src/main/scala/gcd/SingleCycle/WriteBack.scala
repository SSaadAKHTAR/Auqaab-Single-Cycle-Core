package gcd.SingleCycle
import chisel3._
import chisel3.util._

class Write_back extends Module{
    val io= IO(new Bundle{
        val RegWr_enOut = Input(Bool())
        val memtoreg = Input(UInt(2.W))
        val RDsel=Input(UInt(5.W))
        val Memrd=Input(Bool())
        val Dout = Input(SInt(32.W))
        val alu_out = Input(SInt(32.W))
        val pcout = Input(UInt(32.W))
        val brtaken = Input(Bool())
        val pcsel = Input(Bool())
        val bra = Input(Bool())
        val jal = Input(Bool())
        val jalr = Input(Bool())

        val Rd = Output(SInt(32.W))
        val br_taken = Output(Bool())
        val RDselout = Output(UInt(5.W))
        val aluout = Output (SInt(32.W))
        val pcselout = Output(Bool())
        val RegWr_enout = Output(Bool())
        val bform = Output(Bool())
        val jalform = Output(Bool())
        val jalrform = Output(Bool())
    })
    // io.br_taken:=0.B
    io.br_taken:=io.brtaken
    io.aluout:=io.alu_out
    io.pcselout:=io.pcsel
    io.RegWr_enout:=io.RegWr_enOut

    io.bform:=io.bra
    io.jalform:=io.jal
    io.jalrform:=io.jalr
    
    when(io.memtoreg===0.U){
    io.Rd:=io.Dout

   }
   .elsewhen(io.memtoreg===1.U){
    io.Rd:=io.alu_out
   }
   .elsewhen(io.memtoreg===2.U){
    io.Rd:=(io.pcout +4.U).asSInt()
   }
   .otherwise{
    io.Rd:=0.S
   }
   io.RDselout:=io.RDsel

}