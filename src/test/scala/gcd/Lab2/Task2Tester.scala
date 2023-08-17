package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester{
    "Task2tester file " in {
        test(new barrel_shift){ a =>
        a.io.in(0).poke(0.B)
        a.io.in(1).poke(0.B)
        a.io.in(2).poke(1.B)
        a.io.in(3).poke(1.B)
        a.io.sel(0).poke(0.B)
        a.io.sel(1).poke(1.B)
        a.io.shift_type.poke(0.B)
        a.io.out(0).expect(1.B)
        a.io.out(1).expect(1.B)
        a.io.out(2).expect(0.B)
        a.io.out(3).expect(0.B)
        a.clock.step(1)
        }}}





