package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chisel3.iotesters .{ Driver , PeekPokeTester }

class Task1Tester extends FreeSpec with ChiselScalatestTester {
  "lab6 Task1 Tester file" in {
    test(new counter1(13)){a =>
        
        a.clock.step(100)
        a.io.out.expect("b0010".U)
    } 
  }
}

