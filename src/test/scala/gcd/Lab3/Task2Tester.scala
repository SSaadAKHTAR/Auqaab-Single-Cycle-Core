package Lab3
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
import chisel3.fromIntToLiteral
import chisel3._ 


class Task2bTester extends FreeSpec with ChiselScalatestTester {
  "imm_gen Tester file" in {
    test(new ImmdValGen){b =>
       b.io.instr.poke("b00000001010000000000000010010011".U)
      //  b.io.opcode.poke("b0000011".U)
       b.clock.step(1)
       b.io.immd_se.expect(20.S)
       

    }
  }
}