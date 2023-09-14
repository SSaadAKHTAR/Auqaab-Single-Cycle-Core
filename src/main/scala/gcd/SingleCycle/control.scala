package SingleCycle
import chisel3._ 
import chisel3.util._
class contolUnit extends Module{
    val io=IO (new Bundle{
        val instruction = Input(UInt(32.W)) 
        val aluop = Output(UInt(4.W))
        val iform = Output(Bool())
        val rform = Output(Bool())
        val sform = Output(Bool())
        val bform = Output(Bool())
        val rd = Output(UInt(5.W))
        val rs1 = Output(UInt(5.W))
        val rs2 = Output(UInt(5.W)) 
        val wr_en = Output(Bool())
        val mem_wr_en = Output(Bool())
        val wr_back = Output(Bool())
        val immBits = Output(UInt(12.W))
        val mask = Output (Vec(4,Bool()))
        val storefun = Output((UInt)(3.W))
    })
    io.rs1 := 0.U
    io.rs2 := 0.U
    io.mem_wr_en:=0.B
    io.wr_en:=0.B
    io.wr_back:=0.B
    io.storefun:= 0.B
    
    

     val op=WireInit(io.instruction(6,0))
     val f3 = WireInit(io.instruction(14,12))
     val f7 = WireInit(io.instruction(31,25))
     val Rd = WireInit(io.instruction(11,7))
     val Rs1 = WireInit(io.instruction(19,15))
     val Rs2 = WireInit(io.instruction(24,20))
     val imm = WireInit(io.instruction(31,20))
     io.rform:=0.B
     io.iform:=0.B
     io.sform:=0.B
     when(op==="b0110011".U){
      io.rform:=1.B
      val fn3_7 = Cat(f3,f7(5))
      io.aluop:= fn3_7
      io.rs2:=Rs2
      io.wr_en:=1.B
      io.rd:=Rd
     io.rs1:=Rs1
     io.mem_wr_en:=0.B
     io.wr_back:=1.B

     }
     .elsewhen(op==="b0010011".U){
        io.iform:=1.B
        when(f3===5.U||f3===1.U){
          io.aluop:=Cat(f3,f7(5))
        }
        .otherwise{
        io.aluop:=Cat(f3,0.U)
        }
        io.wr_en:=1.B
         io.rd:=Rd
         io.rs1:=Rs1
         io.mem_wr_en:=0.B
         io.wr_back:=1.B
         io.rs2:=0.U
        //  val x=WireInit(io.instruction(31,20))
         io.immBits:=io.instruction(31,20)
        
     }
     .elsewhen(op==="b0000011".U){ //load
        io.iform:=1.B
        io.rd:=Rd
        io.rs1:=Rs1
        io.aluop:=0.U
        io.mem_wr_en:=0.B
        io.wr_back:=0.B
        io.rs2:=0.U
        // val x=WireInit()
        io.immBits:=io.instruction(31,20)
        when(f3===0.U){ // Load Byte
          io.mask(0):= 1.B
          io.mask(1):= 0.B
          io.mask(2):= 0.B
          io.mask(3):= 0.B

        }
        .elsewhen(f3===1.U){ //Load Half word
          io.mask(0):= 1.B
          io.mask(1):= 1.B
          io.mask(2):= 0.B
          io.mask(3):= 0.B

        }
        .elsewhen(f3===2.U){ //Load word
          io.mask(0):= 1.B
          io.mask(1):= 1.B
          io.mask(2):= 1.B
          io.mask(3):= 1.B

        }
        .elsewhen(f3===4.U){ //Load Byte U
          io.mask(0):= 1.B
          io.mask(1):= 0.B
          io.mask(2):= 0.B
          io.mask(3):= 0.B

        }
        .elsewhen(f3===5.U){ //Load Half word U
          io.mask(0):= 1.B
          io.mask(1):= 1.B
          io.mask(2):= 0.B
          io.mask(3):= 0.B

        }

     }
     .elsewhen(op==="b0100011".U){ //Store
      io.wr_en:=0.B
      io.sform:=1.B
      io.rs1:=Rs1
      io.rs2:=Rs2
      // val x=WireInit(io.instruction(11,7))
      // val y=WireInit(io.instruction(31,25))
      io.immBits:=Cat(io.instruction(11,7),io.instruction(31,25))
      io.aluop:=0.U
      io.rd:=0.U
      io.mem_wr_en:=1.B
      io.storefun := io.instruction(14,12)
      // when(f3===0.U){ //Store Byte
      //   io.mask(0):= 1.B
      //   io.mask(1):= 0.B
      //   io.mask(2):= 0.B
      //   io.mask(3):= 0.B

      // }
      // .elsewhen(f3===1.U){ //Store Half word
      //   io.mask(0):= 1.B
      //   io.mask(1):= 1.B
      //   io.mask(2):= 0.B
      //   io.mask(3):= 0.B

      // }
      // .elsewhen(f3===2.U){ // Store Word
      //   io.mask(0):= 1.B
      //   io.mask(1):= 1.B
      //   io.mask(2):= 1.B
      //   io.mask(3):= 1.B

      // }

     }
     .elsewhen(op==="b1100011".U){//Branch
      io.wr_en:=0.B
      io.bform:=1.B
      io.rs1:=Rs1
      io.rs2:=Rs2
      io.aluop:=0.U
      io.rd:=0.U
      io.mem_wr_en:=0.B
      io.immBits:=Cat(io.instruction(31),io.instruction(7),io.instruction(30,25),io.instruction(11,8),0.U)
      io.wr_back:=0.B


     }
     .elsewhen(op==="b0110111".U){//LUI
      io.rd:=Rd
      io.immBits:=io.instruction(31,12)
     }
     .otherwise{
      io.rform:=0.B
      io.iform:=0.B
      io.sform:=0.B
      io.bform:=0.B
      io.wr_back:=0.B
      io.wr_en:=0.B
      io.mem_wr_en:=0.B
      io.rs1:=0.B
      io.rs2:=0.B
      io.rd:=0.B

     }
   
   //   io.aluop:= fn3_7
}

