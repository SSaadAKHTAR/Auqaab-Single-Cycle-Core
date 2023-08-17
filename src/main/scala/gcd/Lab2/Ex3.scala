package Lab2
import chisel3 . _
import chisel3 . util . _
class mux_onehot_4to1 extends Module {
val io = IO (new Bundle {
val in0 = Input ( UInt (1. W ) )
val in1 = Input ( UInt (1. W ) )
val in2 = Input ( UInt (1. W ) )
val in3 = Input ( UInt (1. W ) )
val out = Output ( UInt (1. W ) )
val out2 = Output ( UInt (1. W ) )
})
val s=Cat (io.in1,io.in0)
val a=Cat(io.in3,io.in2)
io . out := Mux1H ( s , Seq ( io . in0 , io . in1  ) )
io.out2 := Mux1H ( a , Seq (  io . in2 , io . in3 ) )
}
// println (( new chisel3 . stage . ChiselStage ) . emitVerilog (new mux_onehot_4to1 () ) )
