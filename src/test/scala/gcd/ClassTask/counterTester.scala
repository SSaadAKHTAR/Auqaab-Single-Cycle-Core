package ClassTask

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class counterTester extends FreeSpec with ChiselScalatestTester {
  "counter Tester file" in {
    test(new counter) { b =>
      b.io.cntin_1.poke("b10101".U)
      b.io.rst.poke(0.B)
      b.io.load.poke(1.B)
      b.io.enable.poke(1.B)
      b.io.result.expect("b10101".U)
      b.reset.poke(0.B)
      b.clock.step(2)

    }
  }}