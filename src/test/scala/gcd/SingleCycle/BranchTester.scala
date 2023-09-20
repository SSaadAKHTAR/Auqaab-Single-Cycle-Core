package SingleCycle

import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _
class BranchTester  extends FreeSpec with ChiselScalatestTester {
"Branch control Tester" in{
    test ( new Branch_Control ) { c =>
        c.io.fun3.poke(0.U)
        c.io.ina.poke(9.S)
        c.io.inb.poke(8.S)
        c.clock.step()
        c.io.br_taken.expect(0.B)



    }}}

