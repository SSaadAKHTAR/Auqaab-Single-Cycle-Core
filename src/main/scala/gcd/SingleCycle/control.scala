package SingleCycle
import chisel3._ 
import chisel3.util._
class contolUnit extends Module{
    val io=IO (new Bundle{
        val instruction = Input(UInt(32.W)) 
        val aluop = Output(UInt(4.W))
        val iform = Output(Bool())
        val rform = Output(Bool())
        val rd = Output(UInt(5.W))
        val rs1 = Output(UInt(5.W))
        val rs2 = Output(UInt(5.W))
        val wr_en = Output(Bool())
    })
    io.rs1 := 0.U
    io.rs2 := 0.U

        io.wr_en:=0.B

     val op=WireInit(io.instruction(6,0))
     val f3 = WireInit(io.instruction(14,12))
     val f7 = WireInit(io.instruction(31,25))
     val Rd = WireInit(io.instruction(11,7))
     val Rs1 = WireInit(io.instruction(19,15))
     val Rs2 = WireInit(io.instruction(24,20))
     val imm = WireInit(io.instruction(31,20))
     io.rform:=0.B
     io.iform:=0.B
     when(op(5)){
        io.rform:=1.B
        val fn3_7 = Cat(f3,f7(5))
      io.aluop:= fn3_7
      io.rs2:=Rs2
      io.wr_en:=1.B
        io.rd:=Rd
     io.rs1:=Rs1
     }
     .otherwise{
        io.iform:=1.B
        io.aluop:=f3
        io.wr_en:=1.B
         io.rd:=Rd
         io.rs1:=Rs1
        
     }

     
   
     
   //   io.aluop:= fn3_7



}

