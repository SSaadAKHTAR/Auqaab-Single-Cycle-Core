package SingleCycle
import chisel3._ 
import chisel3.util._
class contolUnit extends Module{
    val io = IO (new Bundle{
        val instruction = Input(UInt(32.W)) 
        val aluop = Output(UInt(4.W))
        val iform = Output(Bool())
        val rform = Output(Bool())
        val sform = Output(Bool())
        val bform = Output(Bool())
        val luiform = Output(Bool())
        val Jalform = Output(Bool())
        val jalrform = Output(Bool())
        val lform = Output(Bool())
        val Auipc = Output(Bool())
        val rd = Output(UInt(5.W))
        val rs1 = Output(UInt(5.W))
        val rs2 = Output(UInt(5.W)) 
        val wr_en = Output(Bool())
        val mem_wr_en = Output(Bool())
        val wr_back = Output(UInt(2.W))
        val immBits = Output(UInt(12.W))
        val br_fun3 = Output (UInt(3.W))
        //val mask = Output (Vec(4,Bool()))
        val load_storefun = Output(UInt(3.W))

        val pcsel = Output(Bool())
        val btaken = Input(Bool())
        val lui_jal_jalr_auipc_imm = Output(SInt(32.W))
        val mem_r_en = Output(Bool())

      })

    io.rs1 := 0.U
    io.rs2 := 0.U
    io.mem_wr_en:=0.B
    io.wr_en:=0.B
    io.wr_back:=0.U
    io.load_storefun:= 0.U
    io.immBits := 0.B
    io.aluop :=0.U
    io.bform := 0.U
    io.pcsel:=0.B
    io.lui_jal_jalr_auipc_imm:=0.S
    io.br_fun3:=0.U
    io.lform:=0.B
    io.mem_r_en:=0.B

    val op =WireInit(io.instruction(6,0))
     val f3 = WireInit(io.instruction(14,12))
     val f7 = WireInit(io.instruction(31,25))
     val Rd = WireInit(io.instruction(11,7))
     val Rs1 = WireInit(io.instruction(19,15))
     val Rs2 = WireInit(io.instruction(24,20))
     val imm = WireInit(io.instruction(31,20))

     when(branch || jalf || jalrf){
      f3 := 0.U
      f7 := 0.U
      Rd := 0.U      
      Rs1 := 0.U
      Rs2 := 0.U
      imm := 0.U
     }
     .otherwise{
      op :=(io.instruction(6,0))
      f3 := (io.instruction(14,12))
      f7 := (io.instruction(31,25))
      Rd := (io.instruction(11,7))
      Rs1 := (io.instruction(19,15))
      Rs2 := (io.instruction(24,20))
      imm := (io.instruction(31,20))
     }

    

    val branch = RegInit(0.B)
     branch:=io.bform
     val jalf = RegInit(0.B)
     jalf:=io.Jalform
     val jalrf =RegInit(0.B)
     jalrf:=io.jalrform



     
     io.rform:=0.B
     io.iform:=0.B
     io.sform:=0.B
     io.luiform:=0.B
     io.Jalform:=0.B
     io.jalrform:=0.B
     io.Auipc:=0.B
     when(op==="b0110011".U){
      io.rform:=1.B
      val fn3_7 = Cat(f3,f7(5))
      io.aluop:= fn3_7
      io.rs2:=Rs2
      io.wr_en:=1.B
      io.rd:=Rd
     io.rs1:=Rs1
     io.mem_wr_en:=0.B
     io.wr_back:=1.U
     io.br_fun3:=0.U
     io.pcsel:=0.B

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
         io.br_fun3:=0.U
         io.pcsel:=0.B
        
     }
     .elsewhen(op==="b0000011".U){ //load
        io.iform:=1.B
        io.rd:=Rd
        io.lform:=1.B 
        io.rs1:=Rs1
        io.aluop:=0.U
        io.mem_wr_en:=0.B
        io.wr_back:=0.U
        io.rs2:=0.U
        // val x=WireInit()
        io.immBits:=io.instruction(31,20)
        io.load_storefun:=f3
        io.pcsel:=0.B
        io.mem_r_en:=1.B
        io.wr_en := 1.B
        

     }
     .elsewhen(op==="b0100011".U){ //Store
      io.wr_en:=0.B
      io.sform:=1.B
      io.rs1:=Rs1
      io.rs2:=Rs2
      // val x=WireInit(io.instruction(11,7))
      // val y=WireInit(io.instruction(31,25))
      io.immBits:=Cat(io.instruction(31,25),io.instruction(11,7))
      io.aluop:=0.U
      io.rd:=0.U
      io.mem_wr_en:=1.B
      io.load_storefun:=f3
      io.pcsel:=0.B
}

     
     .elsewhen(op==="b1100011".U){//Branch
      io.wr_en:=0.B
      io.bform:=1.B
      io.rs1:=Rs1
      io.rs2:=Rs2
      io.aluop:=0.U
      io.rd:=0.U
      io.mem_wr_en:=0.B
      io.lui_jal_jalr_auipc_imm:=((Cat(Fill(19,io.instruction(31)),io.instruction(31),io.instruction(7),io.instruction(30,25),io.instruction(11,8),0.U))).asSInt()
      io.wr_back:=0.U
      io.br_fun3:=io.instruction(14,12)
      io.pcsel:=Mux(io.btaken && io.bform,1.B,0.B)


     }
     .elsewhen(op==="b0110111".U){//LUI
      io.luiform:=1.B
      io.rd:=Rd
      io.lui_jal_jalr_auipc_imm:=Cat(io.instruction(31,12),Fill(11 ,0.U ),0.U).asSInt()
      io.br_fun3:=0.U
      io.mem_wr_en:=0.B
      io.wr_en:=1.B
      io.wr_back:=1.B
      io.rs1:=0.U
      io.rs2:=0.U
      io.aluop:=0.U
      io.immBits:=0.U
      io.pcsel:=0.B
      
     }
     .elsewhen(op==="b1101111".U){//Jal 
      io.rd:=Rd
      io.lui_jal_jalr_auipc_imm:=Cat(Fill(11,io.instruction(31)),io.instruction(31),io.instruction(19,12),io.instruction(20),io.instruction(30,21),0.U).asSInt()
      io.Jalform:=1.B
      io.aluop:=0.U
      io.rs1:=0.U
      io.rs2:=0.U
      io.mem_wr_en:=0.B
      io.wr_en:=1.B
      io.pcsel:=1.B
      io.wr_back:=2.U



     }
     .elsewhen(op==="b1100111".U){//jalr
      io.lui_jal_jalr_auipc_imm:=Cat(Fill(11,io.instruction(31)),io.instruction(31,20)).asSInt()
      io.rs1:=Rs1
      io.rs2:=0.U
      io.rd:=Rd
      io.mem_wr_en:=0.B
      io.wr_back:=2.U
      io.wr_en:=1.B
      io.pcsel:=1.B
      io.Jalform:=0.B
      io.jalrform:=1.B
      io.aluop:=0.U

     }
     .elsewhen(op==="b0010111".U){//Auipc
      io.rs1:=0.U
      io.rs2:=0.U
      io.rd:=0.U
      io.wr_back:=0.U
      io.mem_wr_en:=0.B
      io.wr_en:=0.B
      io.aluop:=0.U
      io.pcsel:=1.B
      io.Auipc:=1.B
      io.lui_jal_jalr_auipc_imm:=Cat(io.instruction(31,12),Fill(11 ,0.U ),0.U).asSInt()

     

     }
     .otherwise{
      io.rform:=0.B
      io.iform:=0.B
      io.sform:=0.B
      io.bform:=0.B
      io.wr_back:=0.U
      io.wr_en:=0.B
      io.mem_wr_en:=0.B
      io.rs1:=0.B
      io.rs2:=0.B
      io.rd:=0.B
      io.br_fun3:=0.U

     }
     
     




    }
   
   //   io.aluop:= fn3_7

