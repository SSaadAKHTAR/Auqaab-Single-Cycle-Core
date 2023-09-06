package Lab6

import chisel3 . _
import chisel3 . util . _
class counter_with_xor ( val max : Int = 4) extends Module {
val io = IO (new Bundle {
val out = Output ( UInt (( log2Ceil ( max ) . W ) ) )
// val out = Output (UInt(max.W))

})
val count = RegInit(0.U( log2Ceil ( max ) . W ) )
val MSB = count((log2Ceil(max)-1) )
val controll = Reg (Bool())
controll := 1.B ^ MSB
when(controll){
    count:= count + 1.U
}
.otherwise{
    count:= 0.U
}
io.out:= count








}
// println (( new chisel3 . stage . ChiselStage ) . emitVerilog (new counter_with_xor ( n ) ))


