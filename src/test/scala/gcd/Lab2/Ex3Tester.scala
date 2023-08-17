package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Ex3Tester extends FreeSpec with ChiselScalatestTester{
    "Ex3tester file " in {
        test(new mux_onehot_4to1 ){  a =>
        a.io.in0.poke("b0".U)
        a.io.in1.poke("b1".U)
         a.io.in2.poke("b0".U)
          a.io.in3.poke("b0".U)
        //   a.io.sel.poke("b0101".U)
        a.clock.step(1)
        
        a.io.out.expect("b1".U)
        a.io.out2.expect("b0".U)
        }}}

