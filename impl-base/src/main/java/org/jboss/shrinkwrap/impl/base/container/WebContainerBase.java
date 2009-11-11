/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.impl.base.container;

import java.io.File;
import java.net.URL;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Asset;
import org.jboss.shrinkwrap.api.Path;
import org.jboss.shrinkwrap.api.container.WebContainer;
import org.jboss.shrinkwrap.impl.base.Validate;
import org.jboss.shrinkwrap.impl.base.asset.AssetUtil;
import org.jboss.shrinkwrap.impl.base.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.impl.base.asset.FileAsset;
import org.jboss.shrinkwrap.impl.base.asset.UrlAsset;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;

/**
 * WebContainerBase
 * 
 * Abstract class that helps implement the WebContainer. 
 * Used by specs that extends the WebContainer.
 *
 * @author <a href="mailto:aslak@conduct.no">Aslak Knutsen</a>
 * @version $Revision: $
 * @param <T>
 */
public abstract class WebContainerBase<T extends Archive<T>> 
   extends ContainerBase<T> 
   implements WebContainer<T>
{
   //-------------------------------------------------------------------------------------||
   // Class Members ----------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   //-------------------------------------------------------------------------------------||
   // Instance Members -------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||
   
   //-------------------------------------------------------------------------------------||
   // Constructor ------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   protected WebContainerBase(Class<T> actualType, Archive<?> archive) 
   {
      super(actualType, archive);
   }
   
   //-------------------------------------------------------------------------------------||
   // Required Implementations - WebContainer --------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Should be implemented to set the path for Manifest related
    * resources. 
    * 
    * @return Base Path for the ManifestContainer resources
    */
   protected abstract Path getWebPath();

   /* (non-Javadoc)
    * @see org.jboss.declarchive.api.container.WebContainer#setWebXML(java.lang.String)
    */
   @Override
   public T setWebXML(String resourceName) throws IllegalArgumentException
   {
      Validate.notNull(resourceName, "ResourceName should be specified");
      return setWebXML(new ClassLoaderAsset(resourceName));
   }

   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#setWebXML(java.io.File)
    */
   @Override
   public T setWebXML(File resource) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      return setWebXML(new FileAsset(resource));
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#setWebXML(java.net.URL)
    */
   @Override
   public T setWebXML(URL resource) throws IllegalArgumentException 
   {
      Validate.notNull(resource, "Resource should be specified");
      return setWebXML(new UrlAsset(resource));
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#setWebXML(org.jboss.shrinkwrap.api.Asset)
    */
   @Override
   public T setWebXML(Asset resource) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      return addWebResource(resource, "web.xml");
   }

   /* (non-Javadoc)
    * @see org.jboss.declarchive.api.container.WebContainer#addWebResource(java.lang.String)
    */
   @Override
   public T addWebResource(String resourceName) throws IllegalArgumentException
   {
      Validate.notNull(resourceName, "ResourceName should be specified");

      return addWebResource(new ClassLoaderAsset(resourceName), AssetUtil.getNameForClassloaderResource(resourceName));
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.io.File)
    */
   @Override
   public T addWebResource(File resource) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");

      return addWebResource(new FileAsset(resource), resource.getName());
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.lang.String, java.lang.String)
    */
   @Override
   public T addWebResource(String resourceName, String target) throws IllegalArgumentException
   {
      Validate.notNull(resourceName, "ResourceName should be specified");
      Validate.notNull(target, "Target should be specified");

      return addWebResource(new ClassLoaderAsset(resourceName), target);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.io.File, java.lang.String)
    */
   @Override
   public T addWebResource(File resource, String target) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      Validate.notNull(target, "Target should be specified");

      return addWebResource(new FileAsset(resource), target);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.net.URL, java.lang.String)
    */
   @Override
   public T addWebResource(URL resource, String target) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      Validate.notNull(target, "Target should be specified");

      return addWebResource(new UrlAsset(resource), target);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(org.jboss.shrinkwrap.api.Asset, java.lang.String)
    */
   @Override
   public T addWebResource(Asset resource, String target) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      Validate.notNull(target, "Target should be specified");

      return addWebResource(resource, new BasicPath(target));
   }

   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.lang.String, org.jboss.shrinkwrap.api.Path)
    */
   @Override
   public T addWebResource(String resourceName, Path target) throws IllegalArgumentException
   {
      Validate.notNull(resourceName, "ResourceName should be specified");
      Validate.notNull(target, "Target should be specified");
      
      return addWebResource(new ClassLoaderAsset(resourceName), target);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.io.File, org.jboss.shrinkwrap.api.Path)
    */
   @Override
   public T addWebResource(File resource, Path target) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      Validate.notNull(target, "Target should be specified");
      
      return addWebResource(new FileAsset(resource), target);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(java.net.URL, org.jboss.shrinkwrap.api.Path)
    */
   @Override
   public T addWebResource(URL resource, Path target) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      Validate.notNull(target, "Target should be specified");
      
      return addWebResource(new UrlAsset(resource), target);
   }
   
   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.container.WebContainer#addWebResource(org.jboss.shrinkwrap.api.Asset, org.jboss.shrinkwrap.api.Path)
    */
   @Override
   public T addWebResource(Asset resource, Path target) throws IllegalArgumentException
   {
      Validate.notNull(resource, "Resource should be specified");
      Validate.notNull(target, "Target should be specified");
      
      Path location = new BasicPath(getWebPath(), target);
      return add(resource, location);
   }
}
