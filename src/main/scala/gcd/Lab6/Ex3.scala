package Lab6

import chisel3._
import chisel3.util._
class oneShotTimer extends Module{
    val io = IO (new Bundle{
        val reload = Input(Bool())
        val din = Input (UInt(4.W))
        val out = Output (Bool())
        // val done =Input(Bool())
    })
val timer_count = RegInit (0. U (4. W ) )
val done = timer_count === 0. U
val next = WireInit (0. U )
when ( io.reload ) {
next := io.din // load the data from input
}
.elsewhen (~done ) {
next := timer_count - 1. U // decrement the timer
}


timer_count := next // update the timer
val done1 = next === 0. U
when(done1){

    io.out:= 1.B

}
.otherwise{
    io.out:=0.B
}





}