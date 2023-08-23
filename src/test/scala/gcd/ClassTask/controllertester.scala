package ClassTask

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class controllerTester extends FreeSpec with ChiselScalatestTester {
  "controller Tester file" in {
    test(new controller) { b =>
     b.io.op_code.poke(0.U)
     b.io.zero.poke(0.B)
    b.io.rst.poke(0.B)
    b.io.mem_rd.expect(0.B)
    b.io.load_ir.expect(0.B)
    b.io.halt.expect(0.B)
    b.io.inc_pc.expect(0.B)
    b.io.load_ac.expect(0.B)
    b.io.load_pc.expect(0.B)
    b.io.mem_wr.expect(0.B)
      b.clock.step(3)

    }
  }}