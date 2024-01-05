package SingleCycle
import chisel3._
import chisel3.util._

class Reg_File extends Module {
val io = IO (new Bundle {
// val Reg = Output ( Vec (32 ,( UInt (32. W ) ) ) )
// val data = Input(SInt(32.W))
val rd =Input (UInt(5.W))
val rs1 = Input (UInt(5.W))
val rs2 = Input (UInt(5.W))
val Rs1 = Output (SInt(32.W))
val Rs2 = Output (SInt(32.W)) 
val wr = Input (Bool())
val Rd = Input (SInt(32.W)) 
// val chk = Output(SInt(32.W))
})

val Reg2 =  Reg(Vec(32 , SInt (32. W)))  
io.Rs1 := 0.S
io.Rs2 := 0.S

Reg2(0) := 0.S


when(io.wr && io.rd=/=0.U){

Reg2(io.rd):= io.Rd
io.Rs1 := Reg2(io.rs1)
io.Rs2 := Reg2(io.rs2)

}
.otherwise{
    io.Rs1 := Reg2(io.rs1)
    io.Rs2 := Reg2(io.rs2)
}
// io.chk:= Reg2(io.rd)


}