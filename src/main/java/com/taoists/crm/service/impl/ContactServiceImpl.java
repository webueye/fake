package com.taoists.crm.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Contact;
import com.taoists.crm.service.ContactService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-16
 */
@Service("contactService")
public class ContactServiceImpl extends HibernateDaoSupport<Contact> implements ContactService {

}
