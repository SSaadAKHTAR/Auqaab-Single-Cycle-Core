package Lab1

import chisel3._
import chisel3.util._

class Counter3 ( size : Int , maxValue : Int ) extends Module {
val io = IO (new Bundle {
val result = Output ( Bool () )
})
def genCounter ( n : Int , max : Int ) = {
val x = max.asUInt
val count = RegInit (0.U ( n . W ) )
when ( count === x ) {
count := 0. U
} .otherwise {
count := count + 1. U
}
count
}
// genCounter instantiation
val counter1 = genCounter ( size , maxValue )
io . result := counter1 ( size -1)

// println (( new chisel3 . stage . ChiselStage ) . emitVerilog (new Counter (8 , 255. U ) ) )
}

