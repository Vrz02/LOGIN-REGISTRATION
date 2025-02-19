package model;

import operation_implementator.Implementator;

public class Pojo {


		private int port_id;
		private String password;
		private String confirm_password;
		private String location;
		private String role;
		
		
		
		public int getPort_id() {
			return port_id;
		}
		public void setPort_id(int port_id) {
			this.port_id = port_id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirm_password() {
			return confirm_password;
		}
		public void setConfirm_password(String confirm_password) {
			this.confirm_password = confirm_password;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
		

		
		
		public void register_user(Pojo pojo) {
			new Implementator().register_user(pojo);;
		}
		
		public void login_user(Pojo pojo) {
			new Implementator().login_user(pojo);
		}
		
	}

	
	

