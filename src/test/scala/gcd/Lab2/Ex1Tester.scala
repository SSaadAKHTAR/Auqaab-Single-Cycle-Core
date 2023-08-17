package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Ex1Tester extends FreeSpec with ChiselScalatestTester{
    "Ex1tester file " in {
        test(new Mux_2to1){  a =>
            a.io.in_A.poke(1.U)
            a.io.in_B.poke(1.U)
            a.io.select.poke(1.B)
        a.clock.step(12)
        a.io.out.expect(1.U)

        }
    }
}
