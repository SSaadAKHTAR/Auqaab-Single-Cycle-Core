package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Ex2Tester extends FreeSpec with ChiselScalatestTester {
  "lab6 Ex2 Tester file" in {
    test(new counter(8)){a =>
        
        a.clock.step(5)
        a.io.out.expect("b0101".U)
    } 
  }
}

