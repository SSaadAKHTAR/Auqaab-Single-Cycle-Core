package Lab1
//import chiseltest._

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Ex1tester extends FreeSpec with ChiselScalatestTester {
  "CounterTester file" in {
    test(new Counter1(3.S)) { b =>
      b.clock.step(8)
       b.io.result.expect(0.B)

    }
  }}