using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ch15_WebAPI.Models
{
    public interface ISupplierRepository
    {
        IEnumerable<Supplier> GetAll();
        Supplier Find(int id);
    }
}
