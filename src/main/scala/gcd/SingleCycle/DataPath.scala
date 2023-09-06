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

    insMem.io.addr:=pc.io.new_addr
    cu.io.instruction:=insMem.io.inst
    regFile.io.rd:=cu.io.rd
    regFile.io.rs1:=cu.io.rs1
    regFile.io.rs2:=cu.io.rs2
    alu.io.in_A:=regFile.io.Rs1
    imm.io.instr:=insMem.io.inst
    when(cu.io.rform){
    alu.io.in_B:=regFile.io.Rs2
    }
    .otherwise{
        alu.io.in_B:=imm.io.immd_se
    }
    alu.io.alu_op:=cu.io.aluop
    io.out:=alu.io.out
    regFile.io.Rd:=io.out
    regFile.io.wr:=cu.io.wr_en



}
