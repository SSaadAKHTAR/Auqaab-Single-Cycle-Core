package Lab1
import chisel3 . _
import chisel3.util._

class Counter1( counterBits : SInt ) extends Module {
val io = IO (new Bundle {
    val result = Output ( Bool () )
})
val max = ((1. U << counterBits.asUInt ) - 1. U)
val count = (RegInit (0. U (4. W ) ))
when ( count === max ) {
count := 0.U
} .otherwise {
count := count + 1.U
}
io.result := count(15.U )
//println ( s" counter created with max value $max ")
}