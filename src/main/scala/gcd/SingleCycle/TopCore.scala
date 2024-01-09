package SingleCycle
import chisel3._
import chisel3.util._
import gcd.SingleCycle.Write_back
import _root_.javax.swing.plaf.nimbus.AbstractRegionPainter

class TopModule extends Module{
    val io = IO(new Bundle{
        // val datain = Input(UInt(32.W))
        val out = Output(SInt(32.W))
        val pcout = Output(UInt(32.W))
        val ins = Input(UInt(32.W))
        val prevIns = Output(UInt(32.W))
        val bform = Output(Bool())
        val jal = Output(Bool())
        val jalr = Output(Bool())
        // val prevBtaken = Output(Bool())

    })
    val fetch = Module(new fetch)
    val decode = Module(new decode)
    val execute = Module(new execute)
    val memory = Module(new memory)
    val WriteBack = Module(new Write_back)
    io.out:=0.S
    dontTouch(decode.io)
    dontTouch(execute.io)
    dontTouch(memory.io)
    dontTouch(WriteBack.io)

    // io.prevBtaken:=WriteBack.io.br_taken

    val previns = Reg(UInt(32.W))
    previns:= io.ins
    io.prevIns:=previns

    val op = WireInit(io.prevIns(6,0))
    when(op==="b1100011".U){//branch
        io.bform:=1.B
        io.jal:=0.B
        io.jalr:=0.B
    }
    .elsewhen(op==="b1101111".U){//Jal 
        io.bform:=0.B
        io.jal:=1.B
        io.jalr:=0.B
    
    }
    .elsewhen(op==="b1100111".U){//jalr
        io.bform:=0.B
        io.jal:=0.B
        io.jalr:=1.B
    
    }
    .otherwise{
        io.bform:=0.B
        io.jal:=0.B
        io.jalr:=0.B
    }

    val IF_IDins  = Reg(UInt(32.W))
    val jump2 = Reg(Bool())

    when(io.bform  || io.jal || io.jalr){
        IF_IDins:=0.U
        decode.io.ins:=IF_IDins
        jump2:=1.B

    }
    .elsewhen(jump2===1.B){
        IF_IDins:=0.U
        decode.io.ins:=IF_IDins
        jump2:=0.B

    }.otherwise{
        IF_IDins:=fetch.io.instruction
        decode.io.ins:=IF_IDins

    }
    
        

    


    fetch.io.ins:=io.ins

    //Core b\w fetch and decode
    
    
    val IF_IDpc = Reg(UInt(32.W))
    IF_IDpc:= fetch.io.prevPc_out
    decode.io.pcout:=IF_IDpc
    val IF_IDpc4 = Reg(UInt(32.W))
    IF_IDpc4:= fetch.io.pc4_out
    decode.io.pc4out:=IF_IDpc4

    io.pcout:=fetch.io.pc_out

    //core b\w decode and execute
    // val ID_Exiform = Reg(Bool())
    execute.io.iform:=decode.io.iform
    // execute.io.iform:=ID_Exiform
    // val ID_Exrform= Reg(Bool())
    execute.io.rform:=decode.io.rform
    // execute.io.rform:=ID_Exrform
    // val ID_Exsform = Reg(Bool())
    execute.io.sform:=decode.io.sform
    // execute.io.sform:=ID_Exsform
    // val ID_Exbform = Reg(Bool())
    execute.io.bform:=decode.io.bform
    // execute.io.bform:=ID_Exbform
    // val ID_Exluiform = Reg(Bool())
    execute.io.luiform:=decode.io.luiform
    // execute.io.luiform:=ID_Exluiform
    // val ID_Exjalform = Reg(Bool())
    execute.io.Jalform:=decode.io.Jalform
    // execute.io.Jalform:=ID_Exjalform
    // val ID_Exjalrform = Reg(Bool())
    execute.io.jalrform:= decode.io.jalrform
    // execute.io.jalrform:=ID_Exjalrform
    // val ID_Exlform = Reg(Bool())
    execute.io.lform:=decode.io.lform
    // execute.io.lform:=ID_Exlform
    // val ID_ExAuipc = Reg(Bool())
    execute.io.Auipc:=decode.io.Auipc
    // execute.io.Auipc:=ID_ExAuipc
    // val ID_Exlui_jal_jalr_auipc_imm = Reg(SInt(32.W))
    execute.io.lui_jal_jalr_auipc_imm:= decode.io.lui_jal_jalr_auipc_imm
    // execute.io.lui_jal_jalr_auipc_imm:=ID_Exlui_jal_jalr_auipc_imm
    // val ID_EximmBits = Reg(UInt(12.W))
    execute.io.immBits:=decode.io.immBits
    // execute.io.immBits:=ID_EximmBits
    // val ID_Exmem_wr_en = Reg(Bool())
    execute.io.mem_wr_en:=decode.io.mem_wr_en
    // execute.io.mem_wr_en:=ID_Exmem_wr_en
    // val ID_Exwr_back = Reg(UInt(2.W))
    execute.io.wr_back:=decode.io.wr_back
    // execute.io.wr_back:=ID_Exwr_back
    // val ID_Exbr_fun3 = Reg(UInt(3.W))
    execute.io.br_fun3:= decode.io.br_fun3
    // execute.io.br_fun3:=ID_Exbr_fun3
    // val ID_Exload_storefun = Reg(UInt(3.W))
    execute.io.load_storefun:=decode.io.load_storefun
    // execute.io.load_storefun:=ID_Exload_storefun
    // val ID_Expcsel = Reg(Bool())
    execute.io.pcsel:= decode.io.pcsel
    // execute.io.pcsel:=ID_Expcsel
    // val ID_Exmem_r_en = Reg(Bool())
    execute.io.mem_r_en:=decode.io.mem_r_en
    // execute.io.mem_r_en:=ID_Exmem_r_en
    // val ID_Exrs1 = Reg(SInt(32.W))
    execute.io.rs1:=decode.io.rs1
    // execute.io.rs1:=ID_Exrs1
    // val ID_Exrs2 = Reg(SInt(32.W))
    execute.io.rs2:=decode.io.rs2
    // execute.io.rs2:=ID_Exrs2
    // val ID_Exaluop = Reg(UInt(4.W))
    execute.io.aluop:=decode.io.aluop
    // execute.io.aluop:=ID_Exaluop
    // val ID_Expc_out = Reg(UInt(32.W))
    execute.io.pc_out:=decode.io.pc_out
    // execute.io.pc_out:=ID_Expc_out
    // val ID_Eximm_out = Reg(UInt(32.W))
    execute.io.imm_out:= decode.io.imm_out
    // execute.io.imm_out:=ID_Eximm_out
    // val ID_ExRS1 = Reg(UInt(5.W))
    execute.io.RS1:=decode.io.RS1
    // execute.io.RS1:=ID_ExRS1
    // val ID_ExRS2 = Reg(UInt(5.W))
    execute.io.RS2:=decode.io.RS2
    // execute.io.RS2:= ID_ExRS2
    // val ID_ExRd = Reg(UInt(5.W))
    execute.io.Rd := decode.io.Rd
    // execute.io.Rd := ID_ExRd
    // val ID_ExRegWr_en = Reg(UInt(5.W))
    execute.io.RegWr_en:= decode.io.RegWr_en
    // execute.io.RegWr_en:=ID_ExRegWr_en

    //core b\w execute and memory 
    // val ExMemMemWen= Reg(Bool())
    memory.io.Wen:=execute.io.MemWen
    // memory.io.Wen:= ExMemMemWen
    // val ExMemFun3 = Reg(UInt(3.W))
    memory.io.Fun3:=execute.io.MemFun3
    // memory.io.Fun3:=ExMemFun3
    // val ExMemEnable = Reg(Bool())
    memory.io.Enable:=execute.io.MemEnable
    // memory.io.Enable:=ExMemEnable
    // val ExMemDin = Reg (SInt(32.W))
    memory.io.Din:=execute.io.MemDin
    // memory.io.Din:=ExMemDin
    // val ExMemToReg = Reg(UInt(2.W))
    memory.io.MemToReg:=execute.io.MemToReg
    // memory.io.MemToReg:=ExMemToReg
    // val ExMemRDout = Reg(UInt(5.W))
    memory.io.RDout:=execute.io.RDout
    // memory.io.RDout:=ExMemRDout
    // val ExMemRS2out= Reg(UInt(5.W))
    memory.io.RS2out:=execute.io.RS2out
    // memory.io.RS2out:=ExMemRS2out
    // val ExMemRegWr_enout = Reg(Bool())
    memory.io.RegWr_enout:=execute.io.RegWr_enout
    // memory.io.RegWr_enout:=ExMemRegWr_enout
    // val ExMempco = Reg(UInt(32.W))
    memory.io.pco:=execute.io.pco
    // memory.io.pco:=ExMempco
    // val ExMemout = Reg(SInt(32.W))
    memory.io.out:=execute.io.out
    // memory.io.out:=ExMemout
    // val ExMembr_taken = Reg(Bool())
    memory.io.br_taken:=execute.io.br_taken
    // memory.io.br_taken:=ExMembr_taken
    // val ExMempcsel = Reg(Bool())
    memory.io.pcselout:=execute.io.pcselout
    // memory.io.pcselout:=ExMempcsel
    memory.io.bra:=execute.io.bra
    memory.io.jal:=execute.io.jal
    memory.io.jalr:=execute.io.jalr


    //core b\w memory and write back
    // val MemWrbRegWr_enOut = Reg(Bool())
    WriteBack.io.RegWr_enOut:=memory.io.RegWr_enOut
    // WriteBack.io.RegWr_enOut:=MemWrbRegWr_enOut
    // val MemWrbmemtoreg = Reg(UInt(2.W))
    WriteBack.io.memtoreg:=memory.io.memtoreg
    // WriteBack.io.memtoreg:=MemWrbmemtoreg
    // val MemWrbRDsel = Reg(UInt(5.W))
    WriteBack.io.RDsel:=memory.io.RDsel
    // WriteBack.io.RDsel:=MemWrbRDsel
    // val MemWrbMemrd = Reg(Bool())
    WriteBack.io.Memrd:=memory.io.Memrd
    // WriteBack.io.Memrd:=MemWrbMemrd
    // val MemWrbDout = Reg(SInt(32.W))
    WriteBack.io.Dout:=memory.io.Dout
    // WriteBack.io.Dout:=MemWrbDout
    // val MemWrbalu_out = Reg(SInt(32.W))
    WriteBack.io.alu_out:=memory.io.alu_out
    // WriteBack.io.alu_out:=MemWrbalu_out
    // val MemWrbpcout = Reg(UInt(32.W))
    WriteBack.io.pcout:=memory.io.pcout
    // WriteBack.io.pcout:=MemWrbpcout
    // val MemWrbbrtaken=Reg(Bool())
    WriteBack.io.brtaken:=memory.io.brtaken
    // WriteBack.io.brtaken:=MemWrbbrtaken
    // val MemWrbpcsel = Reg(Bool())
    WriteBack.io.pcsel:= memory.io.pcsel
    // WriteBack.io.pcsel:=MemWrbpcsel
    WriteBack.io.bra:=memory.io.bform
    WriteBack.io.jal:=memory.io.jalform
    WriteBack.io.jalr:=memory.io.jalrform
    

    decode.io.btaken:=WriteBack.io.br_taken
    decode.io.Wrbrd:=WriteBack.io.RDselout
    decode.io.din:=WriteBack.io.Rd
    fetch.io.aluout:=WriteBack.io.aluout
    fetch.io.pcsel:= WriteBack.io.pcselout
    decode.io.RegWr_enWB:=WriteBack.io.RegWr_enout
    fetch.io.bform:=WriteBack.io.bform
    fetch.io.jal:=WriteBack.io.jalform
    fetch.io.jalr:=WriteBack.io.jalrform



    io.out := decode.io.din
}