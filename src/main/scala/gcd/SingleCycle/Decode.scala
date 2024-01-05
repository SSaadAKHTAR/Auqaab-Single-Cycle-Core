package SingleCycle
import chisel3._
import chisel3.util._

class decode extends Module{ 
    val io = IO(new Bundle{
        val ins = Input(UInt(32.W))
        // val imm = Input(UInt(32.W))
        val imm_out = Output (UInt(32.W))
        val iform = Output(Bool())
        val rform = Output(Bool())
        val sform = Output(Bool())
        val bform = Output(Bool())
        val luiform = Output(Bool())
        val Jalform = Output(Bool())
        val jalrform = Output(Bool())
        val lform = Output(Bool())
        val Auipc = Output(Bool())
        val lui_jal_jalr_auipc_imm = Output(SInt(32.W))
        val immBits = Output(UInt(12.W))
        // val wr_en = Output(Bool())
        val mem_wr_en = Output(Bool())
        val wr_back = Output(UInt(2.W))
        val br_fun3 = Output (UInt(3.W))
        val load_storefun = Output(UInt(3.W))
        val pcsel = Output(Bool())
        val btaken = Input(Bool())
        val mem_r_en = Output(Bool())
        val rs1 = Output (SInt(32.W))
        val rs2 = Output (SInt(32.W))
        val din = Input (SInt(32.W))
        val aluop = Output(UInt(4.W))
        val pcout = Input(UInt(32.W))
        val pc4out = Input(UInt(32.W))
        val pc_out = Output(UInt(32.W))
        val RS1 = Output(UInt(5.W))
        val RS2 = Output(UInt(5.W)) 
        val Rd = Output(UInt(5.W)) 
        val RegWr_en = Output(Bool())
        val Wrbrd = Input(UInt(5.W))
        val RegWr_enWB = Input (Bool())


    })
    val cu  = Module(new contolUnit)
    val regFile = Module(new Reg_File)
    val imm = Module(new ImmdValGen1)

    cu.io.instruction:= io.ins

    io.RS1:=cu.io.rs1
    io.RS2:=cu.io.rs2
    io.Rd:=cu.io.rd
    io.RegWr_en:=cu.io.wr_en

    io.wr_back:=cu.io.wr_back



    cu.io.btaken:=io.btaken

    io.immBits:= cu.io.immBits
    imm.io.imm:= io.immBits
    io.imm_out:=imm.io.immd_se
    io.lui_jal_jalr_auipc_imm:= cu.io.lui_jal_jalr_auipc_imm

    io.iform:= cu.io.iform
    io.rform:=cu.io.rform
    io.bform:=cu.io.bform
    io.sform:=cu.io.sform
    io.luiform:=cu.io.luiform
    io.Jalform:=cu.io.Jalform
    io.jalrform:=cu.io.jalrform
    io.lform:=cu.io.lform
    io.Auipc:=cu.io.Auipc

    io.mem_wr_en:=cu.io.mem_wr_en
    io.wr_back:=cu.io.wr_back
    io.br_fun3:=cu.io.br_fun3
    io.load_storefun:=cu.io.load_storefun
    io.pcsel:=cu.io.pcsel
    // io.btaken:=cu.io.btaken
    io.mem_r_en:=cu.io.mem_r_en
    io.aluop:=cu.io.aluop

    regFile.io.rd:=io.Wrbrd
    regFile.io.rs1:=cu.io.rs1
    regFile.io.rs2:=cu.io.rs2
    regFile.io.wr:=io.RegWr_enWB
    regFile.io.Rd:=io.din

    io.rs1:=regFile.io.Rs1
    io.rs2:=regFile.io.Rs2

    io.pc_out:=io.pcout
}