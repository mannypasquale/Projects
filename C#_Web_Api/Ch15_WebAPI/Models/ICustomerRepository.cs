using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ch15_WebAPI.Models
{
    public interface ICustomerRepository
    {
        Customer Add(Customer c);
        IEnumerable<Customer> GetAll();
        Customer Find(string id);
        bool Remove(string id);
        Customer Update(string id, Customer c);
    }
}
