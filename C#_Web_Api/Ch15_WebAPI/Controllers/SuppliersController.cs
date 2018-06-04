using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Ch15_WebAPI.Models;

namespace Ch15_WebAPI.Controllers
{
    [Route("api/Suppliers")]
    public class SuppliersController : Controller
    {

        private ISupplierRepository repo;
        private IProductRepository productRepo;
        public SuppliersController(ISupplierRepository repo, IProductRepository productRepo)
        {
            this.repo = repo;
            this.productRepo = productRepo;
        }
        // GET: api/Customers/?productid=[value]
        // Note that we are free to call this method whatever we like.
        // It returns a collection of Customer objects.

        [HttpGet]
        public IEnumerable<Supplier> GetAllSuppliers()
        {
            return repo.GetAll().ToList();
        }
        [HttpGet("{supplierid:int}")]
        public IEnumerable<Supplier> GetSupplier(int supplierid)
        {

            return repo.GetAll()
                .Where(supplier => supplier.SupplierID == supplierid);
        }
        [HttpGet("{supplierid:int}/Products")]
        public IEnumerable<Product> GetProductsBySupplier(int supplierid)
        {

            return productRepo.GetAll()
                .Where(supplier => supplier.SupplierID == supplierid);
        }
    }
}