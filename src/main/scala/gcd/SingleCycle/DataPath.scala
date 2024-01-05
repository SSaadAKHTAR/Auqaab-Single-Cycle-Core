package SingleCycle
import chisel3._ 
import chisel3.util._
class DataPath extends Module{
    val io =IO(new Bundle{
        val out = Output(SInt(32.W))
        val pcout = Output(UInt(32.W))
        val ins = Input(UInt(32.W))


    })

    //val pc = Module(new ProgramCounter)
    val alu = Module(new ALUS)
    val cu  = Module(new contolUnit)
    val imm = Module(new ImmdValGen1)
    // val insMem = Module(new InstMem("./src/main/scala/gcd/SingleCycle/imem.txt"))
    val regFile = Module(new Reg_File)
    val D_Mem = Module(new Datamem)
    val B_control = Module(new Branch_Control)
    // val Syncmem = Module(new SyncMem("./home/saad/Desktop/Single cycle/Scala-Chisel-Learning-Journey/src/main/scala/gcd/SingleCycle/imem.txt"))

    val pc = RegInit(0.U(32.W))

    // when(B_control.io.br_taken){
    //     pc :=alu.io.out.asUInt()
    //     // insMem.io.addr:=alu.io.out

    // }
    
    // pc := pc + 4.U
    pc:=Mux(cu.io.pcsel,alu.io.out.asUInt(),pc + 4.U)
    io.pcout:=pc
    
    cu.io.btaken:=B_control.io.br_taken

    // insMem.io.addr := pc
    // Syncmem.io.add := pc

    cu.io.instruction:=io.ins
    // cu.io.instruction:=Syncmem.io.instOut


    regFile.io.rd:=cu.io.rd
    regFile.io.rs1:=cu.io.rs1
    regFile.io.rs2:=cu.io.rs2
    when(cu.io.bform||cu.io.Jalform){
        alu.io.in_A:= pc.asSInt()
    }
    .otherwise{
     alu.io.in_A:=regFile.io.Rs1
    }


    imm.io.imm:=cu.io.immBits

    when(cu.io.rform){
    alu.io.in_B:=regFile.io.Rs2
    }
    .otherwise{
        when(cu.io.luiform||cu.io.Jalform||cu.io.jalrform||cu.io.Auipc||cu.io.bform){
            alu.io.in_B:=cu.io.lui_jal_jalr_auipc_imm
        }
        .otherwise{
        alu.io.in_B:=imm.io.immd_se.asSInt()
        }
    }
    alu.io.alu_op:=cu.io.aluop

   when(cu.io.wr_back===0.U){
    io.out:=D_Mem.io.Dout

   }
   .elsewhen(cu.io.wr_back===1.U){
    io.out:=alu.io.out
   }
   .elsewhen(cu.io.wr_back===2.U){
    io.out:=(pc +4.U).asSInt()
   }
    .otherwise{
    io.out:=0.S
   }
   
   
    // regFile.io.Rd:=Mux(cu.io.wr_back,alu.io.out,(pc +4.U).asSInt())//D_Mem.io.Dout)
   when(cu.io.wr_back===0.U){
    regFile.io.Rd:=D_Mem.io.Dout

   }
   .elsewhen(cu.io.wr_back===1.U){
    regFile.io.Rd:=alu.io.out
   }
   .elsewhen(cu.io.wr_back===2.U){
    regFile.io.Rd:=(pc +4.U).asSInt()
   }
   .otherwise{
    regFile.io.Rd:=0.S
   }
   
   
   regFile.io.wr:=cu.io.wr_en
    
    

    D_Mem.io.Wen:=cu.io.mem_wr_en
    D_Mem.io.addr:=alu.io.out.asUInt()

    D_Mem.io.fun3 := cu.io.load_storefun
    
   D_Mem.io.enable:=cu.io.mem_r_en
    
    D_Mem.io.Din:=regFile.io.Rs2
    // // io.out:=alu.io.out
    B_control.io.fun3:=cu.io.br_fun3
    B_control.io.ina:=regFile.io.Rs1
    B_control.io.inb:=regFile.io.Rs2

    // D_Mem.io.mask:=cu.io.mask
    




}
