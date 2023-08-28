package Lab5


import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Ex1Tester extends FreeSpec with ChiselScalatestTester {
  "lab5 Tester file" in {
    test(new ALU5(4)){a =>
        a.io.arg_x.poke(3.U)
        a.io.arg_y.poke(2.U)
        a.io.alu_oper.poke("b0000".U)
        a.io.alu_out.expect(2.U)
    a.clock.step(1)

    }
  }
}
