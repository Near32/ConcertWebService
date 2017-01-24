package com.WebService;


import java.util.List;
import java.text.DateFormat;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.*;


import com.WebService.CustomerJDBCTemplate;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
//@SessionAttributes( value = {"Customer", "SpringWeb"}, types ={Customer.class, Object.class})
@SessionAttributes( value = {"CustomerSession","ConcertSession","OrderSession"}, types ={Customer.class,Concert.class,Order.class})
public class WebController 
{
	@Autowired
	private CustomerJDBCTemplate customerJDBC;
	@Autowired
	private ConcertJDBCTemplate concertJDBC;
	@Autowired
	private OrderJDBCTemplate orderJDBC;
	
	
	@ModelAttribute("CustomerSession")
	public Customer getCustomer()
	{
		return new Customer();
	}
	
	
	@ModelAttribute("ConcertSession")
	public Concert getConcert()
	{
		return new Concert();
	}
	
	@ModelAttribute("OrderSession")
	public Order getOrder()
	{
		return new Order();
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	//public String index(HttpSession session, ModelMap model)
	public String index(@ModelAttribute("CustomerSession")Customer customer, HttpSession session, ModelMap model)
	{
		
		if( session.isNew() )
		{
			session.setAttribute("CustomerSession", new Customer() );
			session.setAttribute("ConcertSession", new Concert() );
		}
		

		//Customer customer = (Customer)session.getAttribute("CustomerSession");
		  
	  if( customer.getId() == -1)
	  {
		  model.addAttribute("logged", false);
		  model.addAttribute("customerName", "Unknown");
	  }
	  else
	  {
		 model.addAttribute("logged", true);
		 model.addAttribute("customerName", customer.getFirstName()+" "+customer.getLastName() ); 
	  }
	  
	  return "index";
	}
	
	@RequestMapping(value="/redirectConcertList", method = RequestMethod.GET)
	public String redirectConcertList()
	{
		return "redirect:concerts";
	}
	
	@RequestMapping(value="/redirectLogin", method = RequestMethod.GET)
	public String redirectLogin()
	{
		return "redirect:login";
	}
	
	@RequestMapping(value="/redirectRegistration", method = RequestMethod.GET)
	public String redirectIdentification()
	{
		return "redirect:customer";
	}
	
	@RequestMapping(value = "/concerts", method = RequestMethod.GET)
	public ModelAndView concerts( @ModelAttribute("CustomerSession") Customer customer)
	{
	  List<Concert> concertsList = concertJDBC.getConcertsList();
	  
	  ModelAndView retmodel = new ModelAndView("listConcerts");
	  retmodel.addObject("CustomerSession",customer);
	  retmodel.addObject("concertsList", concertsList);
	  
	  if( customer.getId() == -1)
	  {
		  retmodel.addObject("logged",false);
	  }
	  else
	  {
		 retmodel.addObject("logged",true);
		 retmodel.addObject("userName", customer.getFirstName()+" "+customer.getLastName() );
	  }
	  
	  
	  return retmodel;
	 }
	
	@RequestMapping(value = "/finalPage", method = RequestMethod.GET)
	public String finalPage()
	{
		return "final";
	}
	
	@RequestMapping( value = "/customer", method = RequestMethod.GET)
	public ModelAndView customer()
	{
		return new ModelAndView("customer","command", new Customer() );
	}
	
	@RequestMapping( value = "/addCustomer", method = RequestMethod.POST)
	public ModelAndView addCustomer( @ModelAttribute("CustomerSession")Customer customer)
	{
		this.customerJDBC.create(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber(), customer.getPassword() );
		Customer customerDB = this.customerJDBC.getCustomer(customer.getEmail(), customer.getPassword());
		
		ModelAndView retmodel = new ModelAndView("resultAddCustomer");
		retmodel.addObject("CustomerSession", customerDB);
		retmodel.addObject("firstName", customerDB.getFirstName());
		retmodel.addObject("lastName", customerDB.getLastName());
		retmodel.addObject("email", customerDB.getEmail());
		retmodel.addObject("address", customerDB.getAddress());
		retmodel.addObject("phoneNumber", customerDB.getPhoneNumber());
		
		return retmodel;
	}
	
	@RequestMapping( value = "/makeOrderForm", method = RequestMethod.GET)
	public ModelAndView makeOrderForm( @RequestParam("id")int idConcert, @ModelAttribute("CustomerSession")Customer customer, @ModelAttribute("ConcertSession") Concert concert, @ModelAttribute("OrderSession")Order order)
	{
		ModelAndView retmodel = new ModelAndView("makeOrderForm","command", new Order() );
		retmodel.addObject("ConcertSession", this.concertJDBC.getConcert(idConcert));
		return retmodel;
	}
	
	@RequestMapping( value = "/addOrder", method = RequestMethod.POST)
	public ModelAndView addOrder( @ModelAttribute("CustomerSession")Customer customer, @ModelAttribute("ConcertSession") Concert concert, @ModelAttribute("OrderSession")Order order)
	{
		customer = this.customerJDBC.getCustomer(customer.getEmail(), customer.getPassword());
		
		boolean orderCanBeAdded = true;
		String[] rank = {"A","B","C","D","S"};
		boolean[] seatExceeded = {false,false,false,false,false};
		int[] remainingSeat = {0,0,0,0,0};
		List<Order> ordersforconcert = this.orderJDBC.getOrdersListForConcert(concert.getId());
		
		for( int i=0;i<5;i++)
		{
			int total = 0;
			for(Order o : ordersforconcert)
			{
				if( o.getIdConcert() == concert.getId())
				{
					total += o.getSeat(rank[i]);
				}
			}
			remainingSeat[i] = this.concertJDBC.getNumberSeatForConcert(concert.getId(), rank[i])-total;
		}
		
		//let us check about the number of remaining seats per rank :
		if(remainingSeat[0] < order.getSeatA() )
		{
			seatExceeded[0] = true;
			orderCanBeAdded = false;
		}
		if(remainingSeat[1] < order.getSeatB() )
		{
			seatExceeded[1] = true;
			orderCanBeAdded = false;
		}
		if(remainingSeat[2] < order.getSeatC() )
		{
			seatExceeded[2] = true;
			orderCanBeAdded = false;
		}
		if(remainingSeat[3] < order.getSeatD() )
		{
			seatExceeded[3] = true;
			orderCanBeAdded = false;
		}
		if(remainingSeat[4] < order.getSeatS() )
		{
			seatExceeded[4] = true;
			orderCanBeAdded = false;
		}
		
		
		if(orderCanBeAdded)
		{
			this.orderJDBC.create(order.getSeatA(), order.getSeatB(), order.getSeatC(), order.getSeatD(), order.getSeatS(), customer.getId(), concert.getId() );
			
			return login(customer);
		}
		else
		{
			ModelAndView retmodel = new ModelAndView("unaddedOrder");
			retmodel.addObject("CustomerSession", customer);
			retmodel.addObject("customerName", customer.getFirstName()+" "+customer.getLastName());
			retmodel.addObject("seatAExceeded", seatExceeded[0]);
			retmodel.addObject("seatBExceeded", seatExceeded[1]);
			retmodel.addObject("seatCExceeded", seatExceeded[2]);
			retmodel.addObject("seatDExceeded", seatExceeded[3]);
			retmodel.addObject("seatSExceeded", seatExceeded[4]);
			retmodel.addObject("remainingSeatA", remainingSeat[0]);
			retmodel.addObject("remainingSeatB", remainingSeat[1]);
			retmodel.addObject("remainingSeatC", remainingSeat[2]);
			retmodel.addObject("remainingSeatD", remainingSeat[3]);
			retmodel.addObject("remainingSeatS", remainingSeat[4]);
			return retmodel;
		}
	}
	
	@RequestMapping( value = "/login", method = RequestMethod.GET)
	public ModelAndView login()
	{
		return new ModelAndView("login","command", new Customer() );
	}
	
	@RequestMapping( value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView login( @ModelAttribute("CustomerSession")Customer customer)
	{
		String email = customer.getEmail();
		String password = customer.getPassword();
		
		Customer customerDB = this.customerJDBC.getCustomer(email, password);
		
		if(customerDB.getId() == -1)
		{
			return new ModelAndView("failureLogin");
		}
		else
		{
			customer = customerDB;
			
			ModelAndView retmodel = new ModelAndView("successLogin");
			retmodel.addObject("CustomerSession",customerDB);
			retmodel.addObject("customerName", customerDB.getFirstName()+" "+customerDB.getLastName() );
			
			List<Order> listOrders = orderJDBC.getOrdersListFromCustomer( customerDB.getId() );
			boolean isThereOrders = !( listOrders.isEmpty() );
			
			retmodel.addObject("isThereOrders", isThereOrders);
			retmodel.addObject("ordersList", listOrders);
			
			return retmodel;
		}
		
	}
	
	@RequestMapping(value="/redirectCustomerPage", method=RequestMethod.GET)
	public ModelAndView customerPage(@ModelAttribute("CustomerSession") Customer customer)
	{
		return login(customer);
	}
	
	@RequestMapping(value="/deleteOrder", method=RequestMethod.GET)
	public ModelAndView deleteOrder(@RequestParam("id") int idOrder, @ModelAttribute("CustomerSession") Customer customer)
	{
		String cause = new String();
		
		customer = this.customerJDBC.getCustomer(customer.getEmail(), customer.getPassword());
		// with out that twist, there is a bug with regards to the values of the id of the customer...
		
		if( customer.getId() != -1)
		{
			Order order = this.orderJDBC.getOrder(idOrder);
			
			if( customer.getId() == order.getIdCustomer())
			{
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
				String sConcertDate = order.getDate();
				Date currDate = new Date();
				Date concertDate = new Date();
				try
				{
					concertDate = df.parse(sConcertDate);
				}
				catch( Exception e)
				{
					//there is a serious issue with the state of the database...
				}
				
				
				if( currDate.before( concertDate ) )
				{
					//let us actually delete that order :
					this.orderJDBC.delete(idOrder);
					return login(customer);
				}
				else
				{
					cause = "That concert has already occured.";
				}
			}
			else
			{
				cause = "You haven't made that purchase.";
			}
		}
		else
		{
			cause = "You are not logged in...";
		}
		
		ModelAndView retmodel = new ModelAndView("undeletedOrder");
		retmodel.addObject("CustomerSession",customer);
		retmodel.addObject("Cause", cause);
		
		return retmodel;
	}
	
	@RequestMapping(value="/makeOrder", method=RequestMethod.GET)
	public ModelAndView makeOrder(@RequestParam("idConcer") int idConcert, @ModelAttribute("CustomerSession") Customer customer)
	{
		//that page should be achievable without logging in previously, but we never know...
		if( customer.getId() != -1)
		{
			Concert concert = this.concertJDBC.getConcert(idConcert);
			
			//let us verify that this concert is not past yet :
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			String sConcertDate = concert.getDate();
			Date currDate = new Date();
			Date concertDate = new Date();
			try
			{
				concertDate = df.parse(sConcertDate);
			}
			catch( Exception e)
			{
				//there is a serious issue with the state of the database...
			}
			
			
			if( currDate.before( concertDate ) )
			{
				//let us actually redirect to make an order then :
				ModelAndView retmodel = new ModelAndView("makeOrderForm");
				retmodel.addObject("CustomerSession",customer);
				retmodel.addObject("ConcertSession",concert);
				retmodel.addObject("customerName", customer.getFirstName()+" "+customer.getLastName());
				
				return retmodel;
			}
			
		}
		
		ModelAndView retmodel = new ModelAndView("unmadeOrder");
		retmodel.addObject("CustomerSession",customer);
		
		return retmodel;
	}
}
