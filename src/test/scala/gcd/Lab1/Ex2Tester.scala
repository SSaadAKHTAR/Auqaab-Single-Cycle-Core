package Lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec

class Countermsb1Tester extends FreeSpec with ChiselScalatestTester {
  "Countermsb tester file" in {
    test(new Counter(2)) { a =>
      a.clock.step(10)
      a.io.result.expect(0.U)
    }
  }
}
