package Lab4

import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _
import scala.util.Random

class Task2Tester  extends FreeSpec with ChiselScalatestTester {
"imm Test" in{
    test ( new ImmdValGen4 ) { c =>
    for ( i <- 0 until 100) {
println("i am execued ")
val src_a =Random.nextInt(999) & 0xFFFFFFFFL

// val result = Reg(UInt(12.W))
val y=src_a.asSInt()
val result=WireInit(y(31,20).asSInt())

c.io.instr.poke(src_a.asSInt())
c.io.immd_se.expect(result)
c.clock.step(1)

}

}
}}