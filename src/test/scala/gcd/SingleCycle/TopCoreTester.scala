package SingleCycle

import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class topModuleTester extends FreeSpec with ChiselScalatestTester{
    "top tester" in{
        test (new TopModule){ a =>
            a.clock.step(250)
            // a.io.datain.poke(3.U)
            a.io.out.expect(0.S)

        }
    }
}