package Lab7

import chisel3 . _
import chisel3 . util . _
class arbiter extends Module{
     val io = IO(new Bundle {
    val in = Vec(2, Flipped(Decoupled(UInt(4.W))))
    val out = Decoupled(UInt(4.W))
  })

  val que1 = Module(new Queue(UInt(4.W),4))
  val que2 = Module(new Queue(UInt(4.W),4))

  que1.io.enq <> io.in(0)
  que2.io.enq <> io.in(1)
   val arbiter = Module(new Arbiter(UInt(4.W), 2))
   arbiter.io.in(0) <> que1.io.deq
   arbiter.io.in(1) <> que2.io.deq

   io.out <> arbiter.io.out

}

