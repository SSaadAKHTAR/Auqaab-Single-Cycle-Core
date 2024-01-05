package SingleCycle
import chisel3._
import chisel3.util._

class execute extends Module{
    val io =IO(new Bundle{
        // val in_A = Input(SInt(32.W))
        // val in_B = Input(SInt(32.W))
        // val alu_op = Input(UInt(4.W))
        val out = Output(SInt(32.W))

        // val fun3 =Input(UInt(3.W))
        // val ina = Input(SInt(32.W))
        // val inb = Input(SInt(32.W))
        val br_taken = Output(Bool())

        val iform = Input(Bool())
        val rform = Input(Bool())
        val sform = Input(Bool())
        val bform = Input(Bool())
        val luiform = Input(Bool())
        val Jalform = Input(Bool())
        val jalrform = Input(Bool())
        val lform = Input(Bool())
        val Auipc = Input(Bool())
        val lui_jal_jalr_auipc_imm = Input(SInt(32.W))
        val immBits = Input(UInt(12.W))
        // val wr_en = Input(Bool())
        val mem_wr_en = Input(Bool())
        val wr_back = Input(UInt(2.W))
        val br_fun3 = Input (UInt(3.W))
        val load_storefun = Input(UInt(3.W))
        val pcsel = Input(Bool())
        val mem_r_en = Input(Bool())
        val rs1 = Input (SInt(32.W))
        val rs2 = Input (SInt(32.W))
        val aluop = Input(UInt(4.W))
        val pc_out = Input(UInt(32.W))
        val imm_out = Input (UInt(32.W))
        val RS1 = Input(UInt(5.W))
        val RS2 = Input(UInt(5.W)) 
        val Rd = Input(UInt(5.W)) 
        val RegWr_en = Input(Bool())

        val MemWen = Output(Bool())
        val MemFun3 = Output(UInt(3.W))
        val MemEnable = Output(Bool())
        val MemDin = Output (SInt(32.W))
        val MemToReg = Output(UInt(2.W))
        val RDout = Output(UInt(5.W)) 
        val RS2out = Output(UInt(5.W)) 
        val RegWr_enout = Output(Bool())
        val pco = Output(UInt(32.W))
        val pcselout = Output(Bool())
        val bra = Output(Bool())
        val jal = Output(Bool())
        val jalr = Output(Bool())
    })

    val alu = Module(new ALUS)
    val B_control = Module(new Branch_Control)
    dontTouch(B_control.io)
    // io.br_taken:=0.B
    // val reg = Module(new)
    io.pcselout:=io.pcsel
    io.RegWr_enout:=io.RegWr_en
    io.RDout:=io.Rd
    io.RS2out:=io.RS2
    io.bra:=io.bform
    io.jal:=io.Jalform
    io.jalr:=io.jalrform
     when(io.bform||io.Jalform){
        alu.io.in_A:= io.pc_out.asSInt()
    }
    .otherwise{
     alu.io.in_A:=io.rs1
    }

    when(io.rform){
    alu.io.in_B:=io.rs2
    }
    .otherwise{
        when(io.luiform||io.Jalform||io.jalrform||io.Auipc||io.bform){
            alu.io.in_B:=io.lui_jal_jalr_auipc_imm
        }
        .otherwise{
        alu.io.in_B:=io.imm_out.asSInt()
        }
    }
    
    alu.io.alu_op:=io.aluop
    io.out:=alu.io.out

    B_control.io.fun3:=io.br_fun3
    B_control.io.ina:=io.rs1
    B_control.io.inb:=io.rs2
    io.br_taken:=B_control.io.br_taken

    io.MemWen:=io.mem_wr_en
    io.MemFun3:=io.load_storefun
    io.MemEnable:=io.mem_r_en
    io.MemDin:=io.rs2
    io.MemToReg:=io.wr_back
    io.pco:= io.pc_out





    
}