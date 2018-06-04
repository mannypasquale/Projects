using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ch15_WebAPI.Models
{

    public interface IProductRepository
    {
        Product Add(Product p);
        IEnumerable<Product> GetAll();
        Product Find(int id);
        bool Remove(int id);
        Product Update(int id, Product p);
    }
}
