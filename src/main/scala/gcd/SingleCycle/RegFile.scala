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
val Rs1 = Output (UInt(32.W))
val Rs2 = Output (UInt(32.W)) 
val wr = Input (Bool())
val Rd = Input (UInt(32.W)) 
// val chk = Output(SInt(32.W))
})

val Reg =  Mem (32 , UInt (32. W)) 
io.Rs1 := 0.U
io.Rs2 := 0.U


when(io.wr && io.rd=/=0.U){

Reg(io.rd):= io.Rd
io.Rs1 := Reg(io.rs1)
io.Rs2 := Reg(io.rs2)

}
.otherwise{
    io.Rs1 := Reg(io.rs1)
    io.Rs2 := Reg(io.rs2)
}
// io.chk:= Reg(io.rd)


}