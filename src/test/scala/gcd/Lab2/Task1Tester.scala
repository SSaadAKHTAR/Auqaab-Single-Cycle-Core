package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task1Tester extends FreeSpec with ChiselScalatestTester{
    "Task1tester file " in {
        test(new Mux_5to1){ a =>
        a.io.sel.poke(7.U)
        a.clock.step(1)
        a.io.out.expect(32.U)
        }}}
