package gcd.Lab5


import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import _root_.Lab5.Lab5.Adder
class Task1_5Tester extends FreeSpec with ChiselScalatestTester {
  "lab5 task1 Tester file" in {
    test(new Adder(9)){a =>
        a.io.in0.poke(70.U)
        a.io.in1.poke(30.U)
        a.io.sum.expect(100.U)
    a.clock.step(1)

    } 
  }
}
