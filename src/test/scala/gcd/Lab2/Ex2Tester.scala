package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Ex2Tester extends FreeSpec with ChiselScalatestTester{
    "Ex2tester file " in {
        test(new MuxLookup){  a =>
        a.io.in0.poke(0.B)
        a.io.in1.poke(1.B)
        a.io.in2.poke(0.B)
        a.io.in3.poke(0.B)
        a.io.in4.poke(0.B)
        a.io.in5.poke(0.B)
        a.io.in6.poke(0.B)
        a.io.in7.poke(1.B)
        a.io.sel0.poke(0.U)
        a.io.sel1.poke(1.U)
        a.io.sel2.poke(0.U)
        a.io.out.expect(1.B)
        a.clock.step(12)
        }}} 


