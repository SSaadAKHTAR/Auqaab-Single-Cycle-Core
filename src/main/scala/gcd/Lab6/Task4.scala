package Lab6

import chisel3 . _
import chisel3 . util . _
class up_down_counter ( val max : Int = 10) extends Module {
val io = IO (new Bundle {
val out = Output ( UInt ( log2Ceil ( max ) . W ) )
val up_down = Input ( Bool () )
})
// Start code here
val counter = RegInit (0.U(4.W))
// val up_down = RegInit(1.B)
when(io.up_down){
    counter := counter + 1.U 

}
.otherwise{
    counter := counter - 1.U 

}
io.out:=counter





// End your code here
}


