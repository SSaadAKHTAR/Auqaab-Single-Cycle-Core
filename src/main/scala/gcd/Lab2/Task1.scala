package Lab2
import chisel3 . _
import chisel3.util._
class LM_IO_Interface extends Bundle {
    val sel = Input (UInt (3.W) )
// val s1 = Input ( Bool () )
// val s2 = Input ( Bool () )
val out = Output ( UInt (32. W ) )
}
class Mux_5to1 extends Module {
val io = IO (new LM_IO_Interface )
// Start coding here
io.out:= MuxLookup( io . sel , false .B , Array (
(0.U) -> 0.U,
(1.U) -> 8.U,
(2.U) -> 16.U,
(3.U) -> 24.U,
(4.U) -> 32.U,
(5.U) -> 32.U,
(6.U) -> 32.U,
(7.U) -> 32.U
))
// End your code here
}