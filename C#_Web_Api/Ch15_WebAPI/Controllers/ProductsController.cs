using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Ch15_WebAPI.Models;
namespace Ch15_WebAPI.Controllers
{
    [Route("api/Products")]
    public class ProductsController : Controller
    {

        private IProductRepository repo;
        public ProductsController(IProductRepository repo)
        {

            this.repo = repo;
        }
        // GET: api/Customers/?productid=[value]
        // Note that we are free to call this method whatever we like.
        // It returns a collection of Customer objects.

        [HttpGet]
        public IEnumerable<Product> GetSUppliers(string supplierid)
        {
            if (string.IsNullOrWhiteSpace(supplierid))
                return repo.GetAll();
            return repo.GetAll()
                .Where(product => product.SupplierID == Convert.ToInt32(supplierid));
        }


        [HttpGet("{productid:int}")]
        public IEnumerable<Product> GetProducts(int productid)
        {

            var p = repo.GetAll();
            var thisProduct = p.Where(product => product.ProductID == productid);
            return thisProduct;
        }



        [HttpPut("{id}")]
        public IActionResult Update(int id, [FromBody] Product value)
        {
            if (value == null) // we couldn't deserialize the request body
                return BadRequest(); // HTTP 400
            if (value.ProductID != id)
                return BadRequest(); // HTTP 400
            var existing = repo.Find(id);
            if (existing == null)
                return NotFound(); // HTTP 404
            repo.Update(id, value);
            return new NoContentResult();
        }

        [HttpPost]
        public IActionResult Create([FromBody] Product c)
        {
            if (c == null)
                return BadRequest(); // 400 Bad Request
            repo.Add(c);
            // return a 201 Created with a route to get the object
            // the route will be the value of the Location header in the response.
            return CreatedAtRoute("GetCustomer", new { id = c.ProductID }, c);
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            Product existing = repo.Find(id);
            if (existing == null)
                return NotFound(); // HTTP 404
            repo.Remove(id);
            return new NoContentResult(); // 204 No Content
        }

    }
}