package Lab6

import chisel3 . _
import chisel3 . util . _
class shift_reg_with_parallel_load (val len : Int =3) extends Module {
val io = IO (new Bundle {
val out = Vec ( len , Output ( Bool () ) )
val load_in = Vec ( len , Input ( Bool () ) )
val in = Input ( Bool () )
val load = Input ( Bool () )
})
// Start Coding here
val reg1= RegInit(0.U(len.W))
// val reg2= RegInit(0.B)
// val reg3= RegInit(0.B)
 io.out := reg1.asBools()

when(io.load){
    reg1 := Reverse(io.load_in.asUInt())
}
.otherwise{
    val state = reg1>>1.U | io.in
    reg1 := state

}

}








// End your code here
// Well , you can actually write classes too . So , technically you have nolimit ; )

// println (( new chisel3 . stage . ChiselStage ) . emitVerilog (newshift_reg_with_parallel_load ( n ) ) )


