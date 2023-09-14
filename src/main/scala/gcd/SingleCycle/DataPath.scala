package SingleCycle
import chisel3._ 
import chisel3.util._
class DataPath extends Module{
    val io =IO(new Bundle{
        val out = Output(UInt(32.W))


    })

    val pc = Module(new ProgramCounter)
    val alu = Module(new ALUS)
    val cu  = Module(new contolUnit)
    val imm = Module(new ImmdValGen1)
    val insMem = Module(new InstMem("./src/main/scala/gcd/SingleCycle/imem.txt"))
    val regFile = Module(new Reg_File)
    val D_Mem = Module(new Datamem)
    val B_control = Module(new Branch_Control)
    when(B_control.io.br_taken){
        pc.calNewAddr:=alu.io.out
        // insMem.io.addr:=alu.io.out

    }
    .otherwise{
    insMem.io.addr:=pc.io.new_addr
    }

    cu.io.instruction:=insMem.io.inst

    regFile.io.rd:=cu.io.rd
    regFile.io.rs1:=cu.io.rs1
    regFile.io.rs2:=cu.io.rs2
    when(cu.io.bform){
        alu.io.in_A:=pc.io.new_addr
    }
    .otherwise{
     alu.io.in_A:=regFile.io.Rs1
    }


    imm.io.imm:=cu.io.immBits

    when(cu.io.rform){
    alu.io.in_B:=regFile.io.Rs2
    }
    .otherwise{
        alu.io.in_B:=imm.io.immd_se
    }
    alu.io.alu_op:=cu.io.aluop

    io.out:=Mux(cu.io.wr_back,alu.io.out,D_Mem.io.Dout)
    regFile.io.Rd:=Mux(cu.io.wr_back,alu.io.out,D_Mem.io.Dout)
    regFile.io.wr:=cu.io.wr_en

    D_Mem.io.Wr_en:=cu.io.mem_wr_en
    D_Mem.io.addr:=alu.io.out
    when(cu.io.sform){
        D_Mem.io.fun3 := cu.io.storefun
    }
    
    D_Mem.io.Din:=regFile.io.Rs2
    // // io.out:=alu.io.out
    B_control.io.fun3:=cu.f3
    B_control.io.ina:=regFile.io.Rs1
    B_control.io.inb:=regFile.io.Rs2

    // D_Mem.io.mask:=cu.io.mask
    




}
