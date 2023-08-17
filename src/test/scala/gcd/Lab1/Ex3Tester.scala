package Lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise3test  extends FreeSpec with ChiselScalatestTester {
  "Counter Tester file" in {
    test(new Counter3(3,4)) { b =>
      b.clock.step(8)
      b.io.result.expect(1.B)

    }
  }
}

