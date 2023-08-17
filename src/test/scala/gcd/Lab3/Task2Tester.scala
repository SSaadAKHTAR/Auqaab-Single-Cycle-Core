package Lab3
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
import chisel3.fromIntToLiteral
import chisel3._


class Task2bTester extends FreeSpec with ChiselScalatestTester {
  "imm_gen Tester file" in {
    test(new ImmdValGen){b =>
       b.io.instr.poke("b11111101001100000000000100010011".U)
       b.io.immd_se.expect(-45.S)
       b.clock.step(10)

    }
  }
}