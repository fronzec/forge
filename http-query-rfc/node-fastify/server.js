import Fastify from 'fastify'

const fastify = Fastify({ logger: true })

fastify.addHttpMethod('QUERY', { hasBody: true })

const productos = [
  {
    id: 1,
    nombre: 'Teclado mecanico',
    precio: 120,
    categoria: 'perifericos',
    tags: ['teclado', 'mecanico'],
  },
  {
    id: 2,
    nombre: 'Mouse ergonomico',
    precio: 75,
    categoria: 'perifericos',
    tags: ['mouse', 'ergonomia'],
  },
  {
    id: 3,
    nombre: 'Monitor 27 pulgadas',
    precio: 320,
    categoria: 'monitores',
    tags: ['monitor', 'display'],
  },
  {
    id: 4,
    nombre: 'Base para laptop',
    precio: 45,
    categoria: 'accesorios',
    tags: ['laptop', 'escritorio'],
  },
]

fastify.route({
  method: 'QUERY',
  url: '/productos/buscar',
  handler: async (request, reply) => {
    const body = request.body && typeof request.body === 'object' ? request.body : {}
    const categoria = typeof body.categoria === 'string' && body.categoria.trim()
      ? body.categoria.trim()
      : undefined

    let precioMaximo
    if (body.precioMaximo !== undefined && body.precioMaximo !== null && body.precioMaximo !== '') {
      precioMaximo = Number(body.precioMaximo)

      if (!Number.isFinite(precioMaximo) || precioMaximo < 0) {
        return reply.code(400).send({
          error: 'precioMaximo must be a non-negative number',
        })
      }
    }

    const resultados = productos.filter((producto) => {
      const coincideCategoria = categoria === undefined || producto.categoria === categoria
      const respetaPrecioMaximo = precioMaximo === undefined || producto.precio <= precioMaximo

      return coincideCategoria && respetaPrecioMaximo
    })

    return {
      productos: resultados,
      confirmacion: {
        metodo: request.method,
        filtrosAplicados: {
          categoria: categoria ?? null,
          precioMaximo: precioMaximo ?? null,
        },
        total: resultados.length,
      },
    }
  },
})

try {
  const port = Number(process.env.PORT ?? 3000)
  await fastify.listen({ port })
} catch (error) {
  fastify.log.error(error)
  process.exit(1)
}
