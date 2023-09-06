package gcd.Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "lab5 task2 Tester file" in {
    test(new Router(UInt(10.W))){a =>
        a.io.in.data_field.poke(221.U)
        a.io.in.address_field.poke(6.U)
        a.io.out.data_field.expect(221.U)


    a.clock.step(1)

    } 
  }
}
