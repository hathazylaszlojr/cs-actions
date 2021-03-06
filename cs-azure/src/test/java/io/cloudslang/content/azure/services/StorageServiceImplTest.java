/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.content.azure.services;

import com.microsoft.azure.storage.StorageUri;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URI;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by victor on 10/31/16.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(StorageServiceImpl.class)
public class StorageServiceImplTest {
    @Test(expected = IllegalArgumentException.class)
    public void createContainerThrows() throws Exception {
        StorageServiceImpl.createContainer("", "", "", "", 8080, "", "");
    }

    @Test
    public void createContainer() throws Exception {
        // can't test it because mockito v1 can't mock final classes
    }

    @Test(expected = IllegalArgumentException.class)
    public void listContainersThrows() throws Exception {
        StorageServiceImpl.listContainers("", "", "", 8080, "", "");
    }

    @Test
    public void listContainers() throws Exception {
        // can't test it because mockito v1 can't mock final classes
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteContainerThrows() throws Exception {
        StorageServiceImpl.deleteContainer("", "", "", "", 8080, "", "");
    }

    @Test
    public void deleteContainer() throws Exception {
        // can't test it because mockito v1 can't mock final classes
    }

    @Test(expected = IllegalArgumentException.class)
    public void listBlobsThrows() throws Exception {
        StorageServiceImpl.listBlobs("", "", "", "", 8080, "", "");
    }

    @Test
    public void listBlobs() throws Exception {
        // can't test it because mockito v1 can't mock final classes
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteBlobThrows() throws Exception {
        StorageServiceImpl.deleteBlob("", "", "", "", "", 8080, "", "");
    }

    @Test
    public void deleteBlob() throws Exception {
        // can't test it because mockito v1 can't mock final classes
    }

}