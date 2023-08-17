package Lab3


import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Ex2Tester extends FreeSpec with ChiselScalatestTester {
  "Encoder Tester file" in {
    test(new ALU){a =>
        a.io.in_A.poke(23.U)
        a.io.in_B.poke(23.U)
        a.io.alu_Op.poke("b0001".U)
        a.io.out.expect(0.U)
        a.io.sum.expect(0.U)
    a.clock.step(10)

    }
  }
}
